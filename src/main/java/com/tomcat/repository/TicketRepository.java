package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
