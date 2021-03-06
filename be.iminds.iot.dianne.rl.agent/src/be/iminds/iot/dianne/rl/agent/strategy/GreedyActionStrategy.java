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
package be.iminds.iot.dianne.rl.agent.strategy;

import java.util.Map;

import be.iminds.iot.dianne.api.nn.NeuralNetwork;
import be.iminds.iot.dianne.api.rl.agent.ActionStrategy;
import be.iminds.iot.dianne.api.rl.environment.Environment;
import be.iminds.iot.dianne.nn.util.DianneConfigHandler;
import be.iminds.iot.dianne.rl.agent.strategy.config.GreedyConfig;
import be.iminds.iot.dianne.tensor.Tensor;
import be.iminds.iot.dianne.tensor.TensorOps;

/**
 * Epsilon-greedy action strategy that takes the output of a neural net 
 * and epsilon-greedy select the (discrete) action with the highest output value.
 * 
 * @author tverbele
 *
 */
public class GreedyActionStrategy implements ActionStrategy {
	
	private GreedyConfig config;
	private NeuralNetwork nn;
	
	private Tensor action;

	@Override
	public void setup(Map<String, String> config, Environment env, NeuralNetwork... nns) throws Exception {
		this.config = DianneConfigHandler.getConfig(config, GreedyConfig.class);
		this.nn = nns[0];
		this.action = new Tensor(env.actionDims());
		this.action.fill(0.0f);
		this.action.set(1, (int)(this.action.size()*Math.random()));
	}

	@Override
	public Tensor processIteration(long s, long i, Tensor state) throws Exception {
		double epsilon = config.epsilonMin + (config.epsilonMax - config.epsilonMin) * Math.exp(-s * config.epsilonDecay);
		
		if(config.trace && s % config.traceInterval == 0 && i == 0){
			System.out.println("Epsilon: "+epsilon);
		}
		
		if(config.drop > 0 && Math.random() < config.drop){
			// no-op action?
			action.fill(0.0f);
			return action;
		} else if (Math.random() < epsilon) {
			if(config.momentum > 0.0f){
				if(Math.random() < config.momentum){
					return action;
				}
			} 
			action.fill(0);
			action.set(1, (int) (Math.random() * action.size()));
			return action;
		} else {
			Tensor output = nn.forward(state);
			action.fill(0);
			action.set(1, TensorOps.argmax(output));
			return action;
		}
	}

}
