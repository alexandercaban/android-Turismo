package com.example.alexcaban.finalprojectturismo.Utilities;

public class DefaultItemADT {
	@Override
	public String toString() {
		return "DefaultItemADT [id=" + id + ", sbName=" + sbName + ", blIsSelected=" + blIsSelected + "]";
	}

	private int id;
	private String label;
	private String sbName;
	private boolean blIsSelected;
	private String rut;



	public DefaultItemADT(String sbName, String label) {
		super();
		this.sbName = sbName;
		this.label = label;
	}

	public DefaultItemADT(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public DefaultItemADT(int id, String label, String rut) {
		super();
		this.id = id;
		this.label = label;
		this.rut = rut;
	}

	public DefaultItemADT(int id, String sbName, boolean blIsSelected) {
		super();
		this.id = id;
		this.sbName = sbName;
		this.blIsSelected = blIsSelected;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public boolean isBlIsSelected() {
		return blIsSelected;
	}

	public void setBlIsSelected(boolean blIsSelected) {
		this.blIsSelected = blIsSelected;
	}

	public String getSbName() {
		return sbName;
	}

	public void setSbName(String sbName) {
		this.sbName = sbName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
