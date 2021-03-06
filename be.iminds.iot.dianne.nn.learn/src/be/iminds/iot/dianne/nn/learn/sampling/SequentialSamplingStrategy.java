/*******************************************************************************
 * DIANNE  - Framework for distributed artificial neural networks
 * Copyright (C) 2015  iMinds - IBCN - UGent
 *
 * This file is part of DIANNE.
 *
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Tim Verbelen, Steven Bohez
 *******************************************************************************/
package be.iminds.iot.dianne.nn.learn.sampling;

import be.iminds.iot.dianne.api.dataset.Dataset;
import be.iminds.iot.dianne.api.nn.learn.SamplingStrategy;

public class SequentialSamplingStrategy implements SamplingStrategy {

	private int index;

	private final Dataset dataset;
	
	public SequentialSamplingStrategy(Dataset dataset) {
		this.dataset = dataset;
		this.index = 0;
	}
	
	@Override
	public int next() {
		if(index >= dataset.size()){
			index = 0;
		}
		return index++;
	}

	@Override
	public int[] next(int count){
		int[] indices = new int[count];
		int size = dataset.size();
		for(int i=0;i<count;i++){
			if(index >= size){
				indices[i] = 0;
			} else {
				indices[i] = index++;
			}
		}
		return indices;
	}
}
