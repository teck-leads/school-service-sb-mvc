package com.school.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickLIst implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String code;
	private String capital;
}
