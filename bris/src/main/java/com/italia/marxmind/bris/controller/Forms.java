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
public class Forms {
	private int id;
	private String fileName;
	private String location;
	private String ext;
}
