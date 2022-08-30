package org.techfest.innovate.service;

import java.util.List;

import org.techfest.innovate.entity.Exhibit;

public interface ExhibitService {
	
	List<Exhibit> getAllExhibits();
	Exhibit saveExhibit(Exhibit exhibit);
	Exhibit getExhibitById(long id);
	Exhibit updateExhibit(Exhibit exhibit);
	void deleteExhibit(long id);

}
