/*
 * Copyright (c) 2011-2020, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.io.fiducial;

import georegression.struct.point.Point2D_F64;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a set of Uchiya markers.
 *
 * @author Peter Abeles
 */
public class RandomDotDefinition {
	/** Random seed used to create the markers */
	public long randomSeed = -1;
	/** Diameter of a dot in the marker */
	public double dotDiameter = -1;
	/** Maximum number of dots on a marker */
	public int maxDotsPerMarker = -1;
	/** How wide the marker's bounding rectangle is */
	public double markerWidth = -1;
	/** How tall the marker's bounding rectangle is. If this is not specified then it's assumed to be square */
	public double markerHeight = -1;
	/** Units the the marker is in */
	public String units = "";

	/**
	 * Center coordinates of dots on the markers
	 */
	public final List<List<Point2D_F64>> markers = new ArrayList<>();
}
