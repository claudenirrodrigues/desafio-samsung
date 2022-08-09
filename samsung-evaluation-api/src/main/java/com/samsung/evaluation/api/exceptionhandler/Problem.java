package com.samsung.evaluation.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class Problem {
	
	private Integer status;
	private LocalDateTime dateTime;
	private String title;
	private List<Field> fields;
	
	public Problem(Integer status, LocalDateTime dateTime, String title, List<Field> fields) {
		super();
		this.status = status;
		this.title = title;
		this.dateTime = dateTime;
		this.fields = fields;
	}
	
	@Data
	public static class Field{
		
		private String name;
		private String message;
		
		public Field(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		}
	}
}
