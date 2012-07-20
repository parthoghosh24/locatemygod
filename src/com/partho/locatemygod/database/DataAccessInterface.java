package com.partho.locatemygod.database;

import java.util.ArrayList;

import com.partho.locatemygod.models.Shrine;

public interface DataAccessInterface {

	public Shrine findShrine(long id);
	public ArrayList<Shrine> findAllShrinesByOffsetAndLimit(String pattern, int type, int offset, int limit);
	public ArrayList<Shrine> findAllShrinesByTypeAndOffsetAndLimit(int type, int offset, int limit);
	public ArrayList<Shrine> findAllShrinesByTypeAndFavorite(int type, int offset, int limit);
	public void addShrine(Shrine shrine);
	public void deleteShrine(long id);
	public void updateShrine(Shrine shrine);
	public void updateShrineLocation(long shrineId, double latitude, double longitude);
	public void updateShrineFavorite(String value, long shrineId);
	
}
