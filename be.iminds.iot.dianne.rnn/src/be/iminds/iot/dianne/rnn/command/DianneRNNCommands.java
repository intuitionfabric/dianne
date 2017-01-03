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
package be.iminds.iot.dianne.rnn.command;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import be.iminds.iot.dianne.api.coordinator.DianneCoordinator;
import be.iminds.iot.dianne.api.coordinator.LearnResult;
import be.iminds.iot.dianne.api.nn.Dianne;
import be.iminds.iot.dianne.api.nn.NeuralNetwork;
import be.iminds.iot.dianne.api.nn.module.dto.NeuralNetworkInstanceDTO;
import be.iminds.iot.dianne.api.nn.platform.DiannePlatform;
import be.iminds.iot.dianne.tensor.Tensor;
import be.iminds.iot.dianne.tensor.TensorOps;

/**
 * Separate component for learn commands ... should be moved to the command bundle later on
 */
@Component(
		service=Object.class,
		property={"osgi.command.scope=dianne",
				  "osgi.command.function=generate",
				  "osgi.command.function=bptt"},
		immediate=true)
public class DianneRNNCommands {

	private Dianne dianne;
	private DiannePlatform platform;
	private DianneCoordinator coordinator;

	public void generate(String nnName, String start, int n, String... tags){
		// forward of a rnn
		NeuralNetworkInstanceDTO nni = null;
		try {
			nni = platform.deployNeuralNetwork(nnName, "test rnn", tags);
			NeuralNetwork nn = dianne.getNeuralNetwork(nni).getValue();
			
			String result = ""+start;
			
			for(int i=0;i<start.length()-1;i++){
				nextChar(nn, start.charAt(i));
			}
			
			char c = start.charAt(start.length()-1);
			for(int i=0;i<n;i++){
				c = nextChar(nn, c);
				result += c;
			}
			
			System.out.println(result);
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			platform.undeployNeuralNetwork(nni);
		}
	}
	

	public void bptt(String nnName, String dataset, String... properties){
		Map<String, String> config = createLearnerConfig(properties);
		
		coordinator.learn(dataset, config, nnName.split(",")).then(p -> {
			System.out.println("Learn Job done!");
			LearnResult result = p.getValue();
			System.out.println("Iterations: "+result.getIterations());
			System.out.println("Last minibatch loss: "+result.getLoss());
			return null;
		}, p -> {
			System.out.println("Learn Job failed: "+p.getFailure().getMessage());
			p.getFailure().printStackTrace();
		});
	}

	private Map<String, String> createLearnerConfig(String[] properties){
		
		Map<String, String> config = new HashMap<String, String>();
		// defaults
		config.put("strategy", "be.iminds.iot.dianne.rnn.learn.strategy.BPTTLearningStrategy");

		for(String property : properties){
			String[] p = property.split("=");
			if(p.length==2){
				config.put(p[0].trim(), p[1].trim());
			}
		}
		
		return config;
	}
	
	
	
	private char nextChar(NeuralNetwork nn, char current){
		// construct input tensor
		String[] labels = nn.getOutputLabels();
		if(labels==null){
			throw new RuntimeException("Neural network "+nn.getNeuralNetworkInstance().name+" is not trained and has no labels");
		}
		Tensor in = new Tensor(labels.length);
		in.fill(0.0f);
		int index = 0;
		for(int i=0;i<labels.length;i++){
			if(labels[i].charAt(0)==current){
				index = i;
				break;
			}
		}
		in.set(1.0f, index);
		
		// forward
		Tensor out = nn.forward(in);
		
		// select next, sampling from (Log)Softmax output
		if(TensorOps.min(out) < 0){
			// assume logsoftmax output, take exp
			out = TensorOps.exp(out, out);
		}
		
		double s = 0, r = Math.random();
		int o = 0;
		while(o < out.size() && (s += out.get(o)) < r){
			o++;
		}
		
		return labels[o].charAt(0);
	}
	
	@Reference
	void setDianne(Dianne d){
		this.dianne = d;
	}
	
	@Reference
	void setDiannePlatform(DiannePlatform p){
		this.platform = p;
	}
	
	@Reference
	void setDianneCoordinator(DianneCoordinator c){
		this.coordinator = c;
	}
}
