package com.villatter.service.implementation;

import com.villatter.dto.pdf.HardPdfDTO;
import com.villatter.dto.riwayat.RiwayatRowDTO;
import com.villatter.repository.PendudukRepository;
import com.villatter.repository.RiwayatRepository;
import com.villatter.service.abstraction.SuratServiceInterface;
import com.villatter.utility.PDFLetterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Scope("singleton")
@Service("suratSingletonService")
public class SuratServiceImplementation implements SuratServiceInterface {








}
