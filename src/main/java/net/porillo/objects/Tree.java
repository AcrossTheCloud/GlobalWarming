package net.porillo.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tree {

	private UUID uniqueID;
	private GPlayer owner;
	private GLocation location;
	private int size;

}
