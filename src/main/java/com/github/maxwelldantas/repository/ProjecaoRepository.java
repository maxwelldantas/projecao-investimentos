package com.github.maxwelldantas.repository;

import com.github.maxwelldantas.entity.ProjecaoJurosCompostos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjecaoRepository implements PanacheRepository<ProjecaoJurosCompostos> {
}
