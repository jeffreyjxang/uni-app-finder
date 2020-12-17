package objects;

import javax.swing.*;
import java.util.ArrayList;

public class University {
	private String name;
	private double average;
	private double cutoff;
	private int tuition;
	private int classSize;
	private double latitude;
	private double longitude;
	private int nationalRank;
	private int uniSize;
	private double residenceCost;
	private int ranking;
	private String description;
	private Icon icon;
	private String keywords;
	private Icon logo;
	private String link;


	// constructor method University
	public University(String name, double average, double cutoff, int tuition, int classSize, double latitude,
			double longitude, int nationalRank, int uniSize, double residenceCost, int ranking, String link) {
		this.name = name;
		this.average = average;
		this.cutoff = cutoff;
		this.tuition = tuition;
		this.classSize = classSize;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nationalRank = nationalRank;
		this.uniSize = uniSize;
		this.residenceCost = residenceCost;
		this.ranking = ranking;
		this.link = link;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getCutoff() {
		return cutoff;
	}

	public void setCutoff(double cutoff) {
		this.cutoff = cutoff;
	}

	public int getTuition() {
		return tuition;
	}

	public void setTuition(int tuition) {
		this.tuition = tuition;
	}

	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getNationalRank() {
		return nationalRank;
	}

	public void setNationalRank(int nationalRank) {
		this.nationalRank = nationalRank;
	}

	public int getUniSize() {
		return uniSize;
	}

	public void setUniSize(int uniSize) {
		this.uniSize = uniSize;
	}

	public double getResidenceCost() {
		return residenceCost;
	}

	public void setResidenceCost(double residenceCost) {
		this.residenceCost = residenceCost;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Icon getLogo() {
		return logo;
	}

	public void setLogo(Icon logo) {
		this.logo = logo;
	}

	public String getLink() { return link; }

	public void setLink(String link) {this.link = link; }
}
