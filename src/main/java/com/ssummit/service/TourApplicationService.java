package com.ssummit.service;

import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;
import com.ssummit.model.Tour;
import com.ssummit.model.TourApplication;
import com.ssummit.repository.TourApplicationRepository;
import com.ssummit.util.TourApplicationTemplateFiller;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
public class TourApplicationService extends GenericService<TourApplication> {
	private final TourApplicationRepository repository;
	private final TourService tourService;
	protected TourApplicationService (TourApplicationRepository repository, TourService tourService){
		super(repository);
		this.repository=repository;
		this.tourService = tourService;
	}

	public String sendTourApplication(Long tourId) {
		Tour tour = tourService.getOne(tourId);
		TourApplication tA = new TourApplication();
		tA.setCreatedBy("ADMIN");
		tA.setUpdatedBy("ADMIN");
		tA.setDesctiption(tour.getDescription());
		repository.save(tA);
		Document doc;
		try {
			doc = new Document("src/main/resources/tour_application_template.docx");
		} catch (Exception e) {
			return "Шаблон заявки не найден";
		}
		TourApplicationTemplateFiller tourApplicationTemplateFiller = new TourApplicationTemplateFiller(tour, tA);
		ReportingEngine reportingEngine = new ReportingEngine();
		try {
			reportingEngine.buildReport(doc, tourApplicationTemplateFiller, "s");
		} catch (Exception e) {
			return "Не удалось заполнить шаблон заявки";
		}
		try {
			doc.save(tour.getTitle() + "_заявка в МЧС.docx");
		} catch (Exception e) {
			return "Не удалось сохранить файл";
		}

		return "Заявка на регистрацию группы заполнена и сохранена";
	}

	@Override
	public TourApplication create(TourApplication tourApplication) {
		tourApplication.setIsDeleted(false);
		tourApplication.setCreatedBy("OTLADKA");
		tourApplication.setCreatedDateTime(LocalDateTime.now());
		return super.create(tourApplication);
	}

	@Override
	public TourApplication update(TourApplication tourApplication) {
		tourApplication.setUpdatedBy("OTLADKA");
		tourApplication.setUpdatedDateTime(LocalDateTime.now());
		return super.update(tourApplication);
	}

	@Override
	public void delete(Long id) {
		TourApplication tourApplication = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
		tourApplication.setDeletedBy("OTLADKA");
		tourApplication.setIsDeleted(true);
		tourApplication.setDeletedDateTime(LocalDateTime.now());
		super.update(tourApplication);
	}

}
