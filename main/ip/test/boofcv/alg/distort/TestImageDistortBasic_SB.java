/*
 * Copyright (c) 2011-2016, Peter Abeles. All Rights Reserved.
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

import boofcv.alg.interpolate.InterpolatePixelS;
import boofcv.alg.interpolate.impl.ImplBilinearPixel_F32;
import boofcv.struct.distort.PixelTransform_F32;
import boofcv.struct.image.GrayF32;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Peter Abeles
 */
public class TestImageDistortBasic_SB {

	DummyInterpolate interp = new DummyInterpolate();

	float offX=0,offY=0;

	PixelTransform_F32 tran = new PixelTransform_F32() {
		@Override
		public void compute(int x, int y) {
			distX = x+offX;
			distY = y+offY;
		}
	};

	@Test
	public void applyRenderAll_true() {
		Helper alg = new Helper(interp);
		alg.setRenderAll(true);

		offX= offY=0;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10, 15), new GrayF32(10, 15));
		assertEquals(150, alg.getTotal());

		offX=offY =0.1f;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10, 15), new GrayF32(10, 15));
		assertEquals(150, alg.getTotal());

		offX=offY = -0.1f;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10, 15), new GrayF32(10,15));
		assertEquals(150,alg.getTotal());
	}

	@Test
	public void applyRenderAll_False() {
		Helper alg = new Helper(interp);
		alg.setRenderAll(false);

		offX=offY=0;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10, 15), new GrayF32(10, 15));
		assertEquals(150,alg.getTotal());

		offX=offY=0.1f;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10,15),new GrayF32(10,15));
		assertEquals(9*14,alg.getTotal());

		offX=offY=-0.1f;
		alg.reset();
		alg.setModel(tran);
		alg.apply(new GrayF32(10, 15), new GrayF32(10, 15));
		assertEquals(9*14,alg.getTotal());
	}

	private static class Helper extends ImageDistortBasic_SB {

		int total = 0;

		public Helper(InterpolatePixelS interp) {
			super(interp);
		}

		public void reset() {
			total = 0;
		}

		private int getTotal() {
			return total;
		}

		@Override
		protected void assign(int indexDst, float value) {
			total++;
			int x = (indexDst - dstImg.startIndex)%dstImg.stride;
			int y = (indexDst - dstImg.startIndex)/dstImg.stride;
			assertTrue(dstImg.isInBounds(x,y));
		}
	}

	protected static class DummyInterpolate extends ImplBilinearPixel_F32 {

		@Override
		public float get_border(float x, float y) {
			return 1.1f;
		}
	}


}
