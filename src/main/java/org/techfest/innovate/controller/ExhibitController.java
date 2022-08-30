package org.techfest.innovate.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.techfest.innovate.entity.Exhibit;
import org.techfest.innovate.service.ExhibitService;

@Controller
public class ExhibitController {
	
	private ExhibitService exhibitService;

	public ExhibitController(ExhibitService exhibitService) {
		super();
		this.exhibitService = exhibitService;
	}
	
	//Setup the homepage for the application
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "homepage";
	}
	
	@GetMapping("/exhibits")
	public String listExhibits(Model model) {
		model.addAttribute("exhibits", exhibitService.getAllExhibits());
		return "exhibits";
	}
	
	@GetMapping("/exhibits/new")
	public String createExhibitForm(Model model) {
		
		Exhibit exhibit = new Exhibit();
		model.addAttribute("exhibit", exhibit);
		return "create_exhibit";
	}
	
	@PostMapping("/exhibits")
	public String saveExhibit(@ModelAttribute("exhibit") Exhibit exhibit) {
		exhibit.setMarks(0);
		exhibitService.saveExhibit(exhibit);
		return "redirect:/exhibits";
	}
	
	@GetMapping("/exhibits/edit/{id}")
	public String editExhibitForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("exhibit", exhibitService.getExhibitById(id));
		return "edit_exhibit";
	}
	
	@PostMapping("/exhibits/edit/{id}")
	public String updateExhibit(@ModelAttribute("exhibit") Exhibit exhibit, @PathVariable("id") long id) {
		Exhibit existingExhibit = exhibitService.getExhibitById(id);
		
		existingExhibit.setContactNo(exhibit.getContactNo());
		existingExhibit.setDomain(exhibit.getDomain());
		existingExhibit.setEmail(exhibit.getEmail());
		existingExhibit.setId(id);
		existingExhibit.setName(exhibit.getName());
		existingExhibit.setTitle(exhibit.getTitle());
		exhibit.setMarks(0);
		
		exhibitService.saveExhibit(existingExhibit);
		return "redirect:/exhibits";
	}
	
	@GetMapping("/exhibits/delete/{id}")
	public String deleteExhibit(@PathVariable("id") long id) {
		exhibitService.deleteExhibit(id);
		return "redirect:/exhibits";
	}
	
	@GetMapping("/exhibits/evaluate/{id}")
	public String getEvaluationForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("exhibit", exhibitService.getExhibitById(id));
		return "evaluate_exhibits";
	}
	
	@PostMapping("/exhibits/evaluate/{id}")
	public String evaluateExhibit(@ModelAttribute("exhibit") Exhibit exhibit, @PathVariable("id") long id) {
		Exhibit existingExhibit = exhibitService.getExhibitById(id);
		
		existingExhibit.setMarks(exhibit.getMarks());
		existingExhibit.setId(id);
		
		exhibitService.saveExhibit(existingExhibit);
		
		return "redirect:/exhibits";
	}
	
	@GetMapping("/exhibits/evaluated")
	public String listEvaluatedExhibits(Model model) {
		List<Exhibit> exhibitList = exhibitService.getAllExhibits();
		
		Comparator<Exhibit> marksComparator = Comparator.comparing(Exhibit :: getMarks);
		exhibitList.sort(marksComparator);
		Collections.reverse(exhibitList);
		model.addAttribute("exhibits", exhibitList);
		return "evaluated_exhibits";
	}
}
