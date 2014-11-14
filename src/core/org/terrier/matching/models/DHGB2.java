/*
 * Terrier - Terabyte Retriever 
 * Webpage: http://terrier.org 
 * Contact: terrier{a.}dcs.gla.ac.uk
 * University of Glasgow - School of Computing Science
 * http://www.gla.ac.uk/
 * 
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is TF_IDF.java.
 *
 * The Original Code is Copyright (C) 2004-2011 the University of Glasgow.
 * All Rights Reserved.
 *
 * Contributor(s):
 *   Ben He <ben{a.}dcs.gla.ac.uk> (original author)
 *   Gianni Amati <gba{a.}fub.it> 
 *   Vassilis Plachouras <vassilis{a.}dcs.gla.ac.uk>
 */
package org.terrier.matching.models;
/**
 * This class implements the TF_IDF weighting model.
 * tf is given by Robertson's tf and idf is given by the 
 * standard Sparck Jones' idf [Sparck Jones, 1972].
 * @author Ben He, Gianni Amati, Vassilis Plachouras
  */
public class DHGB2 extends WeightingModel {
	
	private static final long serialVersionUID = 1L;

	/** model name */
	private static final String name = "DHGB2";

	/** The constant k_1.*/
	private double k_1 = 1.2d;
	
	/** The constant b.*/
	private double b = 0.75d;

	/** 
	 * A default constructor to make this model.
	 */
	public DHGB2() {
		super();
	}
	/** 
	 * Constructs an instance of DHGB2
	 * @param _b
	 */
	public DHGB2(double _b) {
		this();
		this.b = _b;
	}

	/**
	 * Returns the name of the model, in this case "DHGB2"
	 * @return the name of the model
	 */
	public final String getInfo() {
		return name;
	}
	/**
	 * Uses TF to compute a weight for a term in a document.
	 * @param tf The term frequency of the term in the document
	 * @param docLength the document's length
	 * @return the score assigned to a document with the given 
	 *		 tf and docLength, and other preset parameters
	 */
	public final double score(double tf, double docLength) {
	    double tf1 = Idf.log(tf);
	    double idf1 = Idf.log(numberOfDocuments/documentFrequency + 1.0);
	    double wt = tf1 * idf1;
	    return keyFrequency * wt;
	}
	/**
	 * Uses TF to compute a weight for a term in a document.
	 * @param tf The term frequency of the term in the document
	 * @param docLength the document's length
	 * @param documentFrequency The document frequency of the term (ignored)
	 * @param termFrequency the term frequency in the collection (ignored)
	 * @param keyFrequency the term frequency in the query (ignored).
	 * @return the score assigned by the weighting model TF.
	 */
	public final double score(
		double tf,
		double docLength,
		double documentFrequency,
		double termFrequency,
		double keyFrequency) 
	{
	    double tf1 = Idf.log(tf);
	    double idf1 = Idf.log(numberOfDocuments/documentFrequency + 1.0);
	    double wt = tf1 * idf1;
	    return keyFrequency * wt;
	}

	/**
	 * Sets the b parameter to ranking formula
	 * @param _b the b parameter value to use.
	 */
	public void setParameter(double _b) {
		this.b = _b;
	}


	/**
	 * Returns the b parameter to the ranking formula as set by setParameter()
	 */
	public double getParameter() {
		return this.b;
	}
}
