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
 *     Steven Bohez
 *******************************************************************************/
package be.iminds.iot.dianne.nn.util.random.distributions;

import be.iminds.iot.dianne.nn.util.random.Distribution;
import be.iminds.iot.dianne.nn.util.random.DistributionFactory;

public class NormalDistributionFactory extends DistributionFactory{

	private final Distribution mu;
	private final Distribution sigma;
	
	public NormalDistributionFactory(Distribution mu, Distribution sigma){
		super();
		this.mu = mu;
		this.sigma = sigma;
	}
	
	@Override
	public Distribution nextDistribution(long seed) {
		return new NormalDistribution(seed,mu.nextDouble(),sigma.nextDouble());
	}

	@Override
	public Distribution nextDistribution() {
		return new NormalDistribution(mu.nextDouble(),sigma.nextDouble());
	}
}
