package org.techfest.innovate.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.techfest.innovate.entity.Exhibit;
import org.techfest.innovate.repository.ExhibitRepository;
import org.techfest.innovate.service.ExhibitService;

@Service
public class ExhibitServiceImpl implements ExhibitService {
	private ExhibitRepository exhibitRepository;

	//Constructor based dependency injection
	public ExhibitServiceImpl(ExhibitRepository exhibitRepository) {
		super();
		this.exhibitRepository = exhibitRepository;
	}

	@Override
	public List<Exhibit> getAllExhibits() {
		return exhibitRepository.findAll();
	}

	@Override
	public Exhibit saveExhibit(Exhibit exhibit) {
		return exhibitRepository.save(exhibit);
	}

	@Override
	public Exhibit updateExhibit(Exhibit exhibit) {
		return exhibitRepository.save(exhibit);
	}

	@Override
	public void deleteExhibit(long id) {
		exhibitRepository.deleteById(id);
	}

	@Override
	public Exhibit getExhibitById(long id) {
		return exhibitRepository.findById(id).get();
	}
	
	
}
