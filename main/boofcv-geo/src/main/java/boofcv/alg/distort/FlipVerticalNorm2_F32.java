/*
 * Copyright (c) 2011-2019, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.distort;

import boofcv.struct.distort.Point2Transform2_F32;
import georegression.struct.point.Point2D_F32;

/**
 * <p>
 * Flips the image along the vertical axis and convert to normalized image coordinates using the
 * provided transform.
 * </p>
 *
 * @author Peter Abeles
 */
public class FlipVerticalNorm2_F32 implements Point2Transform2_F32 {

	Point2Transform2_F32 pixelToNormalized;
	int height;

	public FlipVerticalNorm2_F32(Point2Transform2_F32 pixelToNormalized, int imageHeight) {
		this.pixelToNormalized = pixelToNormalized;
		this.height = imageHeight - 1;
	}

	@Override
	public void compute(float x, float y, Point2D_F32 out) {
		pixelToNormalized.compute(x, height - y, out);
	}

	@Override
	public FlipVerticalNorm2_F32 copyConcurrent() {
		return new FlipVerticalNorm2_F32(pixelToNormalized.copyConcurrent(),height);
	}

}
