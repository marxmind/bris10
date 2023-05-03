package com.italia.marxmind.bris.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class BrisFile {
	private int id;
	private String name;
	private String ext;
	private String path;
	private String type;
	private String icon;
	private Object object;
}
