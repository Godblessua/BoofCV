/*
 * Copyright (c) 2011-2012, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.feature.detect.grid;

import boofcv.alg.misc.ImageTestingOps;
import boofcv.struct.image.ImageUInt8;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Peter Abeles
 */
public class TestDetectSpacedSquareGrid {

	/**
	 * Create a synthetic target and see if it can detect it
	 */
	@Test
	public void basicTest() {
		int squareLength = 30;
		int w = 400;
		int h = 500;
		ImageUInt8 binary = new ImageUInt8(w,h);

		// create the grid
		for( int y = 0; y < 3; y++) {
			for( int x = 0; x < 4; x++ ) {
				int pixelY = y*(squareLength+10)+10;
				int pixelX = x*(squareLength+10)+15;

				ImageTestingOps.fillRectangle(binary,1,pixelX,pixelY,squareLength,squareLength);
			}
		}
		
		// add a bit of noise
		ImageTestingOps.fillRectangle(binary,1,400,100,25,22);

		DetectSpacedSquareGrid alg = new DetectSpacedSquareGrid(100,3,4);

		assertTrue(alg.process(binary));

		List<SquareBlob> squares = alg.getSquaresOrdered();
		
		assertEquals(12,squares.size());
		
		SquareBlob b = squares.get(0);
		assertEquals(15,b.corners.get(0).x);
		assertEquals(10,b.corners.get(0).y);
	}
}
