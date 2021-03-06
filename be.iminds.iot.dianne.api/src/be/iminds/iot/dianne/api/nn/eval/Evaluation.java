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
package be.iminds.iot.dianne.api.nn.eval;

/**
 * Result an the evaluation... contains the number of entities (samples/sequences) evaluated,
 * the resulting metric and the time required to evaluate 
 * 
 * @author tverbele
 *
 */
public class Evaluation {

	public long size;
	public float metric;
	public long time;
	
	@Override
	public String toString(){
		return "Metric: "+metric+"\tSize: "+size+"\tTime: "+time+" ms.";
	}
	
	/**
	 * @return the total evaluation time
	 */
	public long time(){
		return time;
	}
	
	/**
	 * @return the total number of samples/sequences on which the evaluation took place
	 */
	public long size(){
		return size;
	}
	
	/**
	 * @return evaluation metric on the data processed
	 */
	public float metric() {
		return metric;
	}

}
