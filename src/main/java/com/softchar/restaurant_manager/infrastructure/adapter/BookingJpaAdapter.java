package com.softchar.restaurant_manager.infrastructure.adapter;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.BookingEntity;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.TableEntity;
import com.softchar.restaurant_manager.infrastructure.adapter.mapper.BookingDboMapper;
import com.softchar.restaurant_manager.infrastructure.adapter.mapper.TableDboMapper;
import com.softchar.restaurant_manager.infrastructure.adapter.repository.BookingJpaRepository;
import com.softchar.restaurant_manager.infrastructure.adapter.repository.TableJpaRepository;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.MethodArgumentNotValidException;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.NameCannotBeNullException;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.ResourceNotFoundException;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.TableAlreadyBookedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingJpaAdapter implements BookingRepositoryPort {

    private final BookingJpaRepository jpaBookingRepository;
    private final TableJpaRepository jpaTableRepository;
    private final BookingDboMapper bookingDboMapper;
    private final TableDboMapper tableDboMapper;

    @Autowired
    public BookingJpaAdapter(
            BookingJpaRepository jpaBookingRepository,
            TableJpaRepository jpaTableRepository,
            TableDboMapper tableDboMapper,
            BookingDboMapper bookingDboMapper
    ) {
        this.jpaBookingRepository = jpaBookingRepository;
        this.jpaTableRepository = jpaTableRepository;
        this.bookingDboMapper = bookingDboMapper;
        this.tableDboMapper = tableDboMapper;
    }

    @Override
    public Booking save(Booking request) {
        if (checkDateAndTime(request, checkTableAvailability(request))) {
            BookingEntity newBooking = bookingDboMapper.toDbo(request);
            BookingEntity bookingSaved = jpaBookingRepository.save(newBooking);
            return bookingDboMapper.toDomain(bookingSaved);
        }
        else throw new TableAlreadyBookedException("Table with ID " + request.getTable().getId() + " is already booked for the specified date and time");
    }

    @Override
    public Booking updateById(Long id, Booking request) {
        BookingEntity existingBookingEntity = jpaBookingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Booking not found whit ID: " + id)
        );
        if(checkDateAndTime(request, checkTableAvailability(request))) {
            existingBookingEntity.setCustomerDni(request.getCustomerDni());
            existingBookingEntity.setCustomerName(request.getCustomerName());
            existingBookingEntity.setTable(checkTableAvailability(request));
            existingBookingEntity.setReservationDate(request.getReservationDate());
            existingBookingEntity.setReservationTime(request.getReservationTime());

            BookingEntity bookingUpdated = jpaBookingRepository.save(existingBookingEntity);
            return bookingDboMapper.toDomain(bookingUpdated);
        }
        else throw new TableAlreadyBookedException("Table with ID " + request.getTable().getId() + " is already booked for the specified date and time");
    }

    @Override
    public Booking findById(Long id) {
       BookingEntity bookingEntity = jpaBookingRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Booking not found whit ID: " + id)
       );
        return bookingDboMapper.toDomain(bookingEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaBookingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Booking not found whit ID: " + id)
        );
        jpaBookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findAllByName(String name) {
        List<BookingEntity> bookingEntities = jpaBookingRepository.findAllByCustomerNameContaining(name);
        if(bookingEntities.isEmpty())
            throw new NameCannotBeNullException("Booking not found whit customerName: " + name);

        return bookingEntities.stream()
                .map(bookingDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        Page<BookingEntity> bookingEntities = jpaBookingRepository.findAll(pageable);
        return bookingEntities.map(bookingDboMapper::toDomain);
    }


    private TableEntity checkTableAvailability(Booking booking){
        return jpaTableRepository.findById(booking.getTable().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Table with ID " + booking.getTable().getId() + " not found"));
    }

    private boolean checkDateAndTime(Booking booking, TableEntity table){
        return !jpaBookingRepository.existsByTableAndReservationDateAndReservationTime(
                table, booking.getReservationDate(), booking.getReservationTime());
    }

}
