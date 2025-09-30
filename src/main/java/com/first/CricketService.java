package com.first;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CricketService {

	private CricketRepository cr;
	public CricketService(CricketRepository cr)
	{
		this.cr=cr;
	}
	
	public Cricket postSingle(Cricket c)
	{
		return cr.save(c);
	}
	
	public List<Cricket> postAll(List<Cricket> c)
	{
		return cr.saveAll(c);
	}
	
	public List<Cricket> getAll()
	{
		return cr.findAll();
	}
	
	public Optional<Cricket> getById(Long a)
	{
		return cr.findById(a);
	}
	
	public Cricket update(Long a,Cricket c)
	{
		return cr.findById(a).map(e -> {
			e.setName(c.getName());
			e.setTeam(c.getTeam());
			e.setRuns(c.getRuns());
			return cr.save(e);
		}).orElseThrow(() -> new RuntimeException("Not Found"));
	}
	
	public void delete(Long a)
	{
		cr.deleteById(a);
	}
}
