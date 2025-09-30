package com.first;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket")
public class CricketController {

	private CricketService cs;
	public CricketController(CricketService cs)
	{
		this.cs=cs;
	}
	
	@PostMapping("/postsingle")
	public ResponseEntity<Cricket> postSingle(@RequestBody Cricket c)
	{
		return ResponseEntity.ok(cs.postSingle(c));
	}
	
	@PostMapping("/postall")
	public ResponseEntity<List<Cricket>> postAll(@RequestBody List<Cricket> c)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cs.postAll(c));
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Cricket>> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(cs.getAll());
	}
	
	@GetMapping("/getbyid/{a}")
	public ResponseEntity<Cricket> getById(@PathVariable Long a)
	{
		return cs.getById(a).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/update/{a}")
	public ResponseEntity<Cricket> update(@PathVariable Long a,@RequestBody Cricket c)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cs.update(a,c));
	}
	
	@DeleteMapping("/delete/{a}")
	public ResponseEntity<Void> delete(@PathVariable Long a)
	{
		cs.delete(a);
		return ResponseEntity.noContent().build();
	}
}
