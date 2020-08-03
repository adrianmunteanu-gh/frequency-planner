package com.frequency.frequencyplanner.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.frequency.frequencyplanner.domain.FrequencyBand;
import com.frequency.frequencyplanner.domain.FrequencyPlan;
import com.frequency.frequencyplanner.domain.Site;
import com.frequency.frequencyplanner.domain.Transmitter;

public class FrequencyPlanGenerator {
	public FrequencyPlan generator(int f, int t, int s, int n, int g) {
		List<FrequencyBand> frequencyList = new ArrayList<FrequencyBand>();
		List<Site> siteList = new ArrayList<Site>();
		List<Transmitter> transmitterList = new ArrayList<Transmitter>();

		frequencyList = generateFrequencyList(f, g);
		siteList = generateSiteList(t, s);
		transmitterList = generateTransmitterList(siteList, t, s);
		siteList = generateNeighbours(siteList, n, t, s);
		FrequencyPlan problem = new FrequencyPlan(frequencyList, transmitterList, siteList);
		return problem;
	}

	@SuppressWarnings("serial")
	public List<FrequencyBand> generateFrequencyList(int f, int g) { // f=number of frequencies | g=number of frequency
																		// groups
		List<FrequencyBand> frequencyList = new ArrayList<FrequencyBand>();
		List<ArrayList<Integer>> frequencyGroup = new ArrayList<ArrayList<Integer>>();
		Random rnd = new Random();
		
		for (int i = 0; i < g; i++)
			frequencyGroup.add(new ArrayList<Integer>());
		
		for (int i = 1; i <= f; i++) {
			int gr = rnd.nextInt(g);
			frequencyGroup.get(gr).add(i);
		}

		for (int i = 0; i < g-1; i++) {
			for (int j = 0; j < frequencyGroup.get(i).size(); j++)
				frequencyList.add(new FrequencyBand(frequencyGroup.get(i), j));
		}
		for (int i = 0; i < frequencyGroup.get(g-1).size(); i++) {
			int j=frequencyGroup.get(g-1).get(i);
				frequencyList.add(new FrequencyBand(
						new ArrayList<Integer>() {{add(j);}}, 0));
		}
		return frequencyList;
	}

	public List<Site> generateSiteList(int t, int s) { // t=number of transmitters | s=number of transmitters per site
		List<Site> siteList = new ArrayList<Site>();
		for (int i = 1; i <= t / s; i++) {
			siteList.add(new Site(i));
		}
		return siteList;
	}

	public List<Transmitter> generateTransmitterList(List<Site> siteList, int t, int s) {
		List<Transmitter> transmitterList = new ArrayList<Transmitter>();
		Random rnd = new Random();
		for (int i = 1; i <= t; i++) {
			transmitterList.add(new Transmitter(i));
			int site = rnd.nextInt(t / s);
			transmitterList.get(i - 1).setSite(siteList.get(site));
			siteList.get(site).addTransmitter(transmitterList.get(i - 1));
			siteList.get(site).addTransmitterId(transmitterList.get(i - 1).getId() - 1);
		}
		return transmitterList;
	}

	public List<Site> generateNeighbours(List<Site> siteList, int n, int t, int s) { // n=number of neighbours
		Random rnd = new Random();
		int site;
		for (int i = 0; i < siteList.size(); i++) {
			for (int j = 0; j < ((n / 2) + 1) / s; j++) {
				do {
					site = rnd.nextInt(t / s);
				} while (site == i || checkSiteN(siteList.get(site).getId(), siteList.get(i)));

				siteList.get(i).addNeighbour(siteList.get(site));
				siteList.get(site).addNeighbour(siteList.get(i));

				siteList.get(i).update(setNeighbours(siteList.get(i), siteList.get(site)));
				siteList.get(site).update(setNeighbours(siteList.get(site), siteList.get(i)));
			}
		}
		return siteList;
	}

	private boolean checkSiteN(int n, Site site) {
		for (int i = 0; i < site.getNeighbours().size(); i++) {
			if (site.getNeighbours().get(i).getId() == n)
				return true;
		}
		return false;
	}

	private Site setNeighbours(Site site1, Site site2) {
		for (int i = 0; i < site1.getTransmitters().size(); i++)
			site1.getTransmitters().get(i).setNeighbours(
					generateTransmitterN(site1.getTransmitters().get(i).getNeighbours(), site2.getTransmitters()));
		return site1;
	}

	private List<Transmitter> generateTransmitterN(List<Transmitter> neighbourList, List<Transmitter> neighbours) {
		neighbourList.addAll(neighbours);
		return neighbourList;
	}
}
