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
package be.iminds.iot.dianne.rl.learn.config;


public class DeepQLearnerConfig {

	/**
	 * Discount factor
	 */
	public float discount = 0.99f;
	
	/**
	 * Interval to update the target NN
	 */
	public int targetInterval = 1000;


	/**
	 * Size of the batches that are processed by the Learner
	 */
	public int batchSize = 10;
	
	/**
	 * Minimum samples that should be in the Experience Pool before training starts
	 */
	public int minSamples = 1000;

	
	/**
	 * Interval to store the intermediate weights during training 
	 */
	public int storeInterval = -1;
	
	/**
	 * Tag to store the weights with
	 */
	public String storeTag;
	
}