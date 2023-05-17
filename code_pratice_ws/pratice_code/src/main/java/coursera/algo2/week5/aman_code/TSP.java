package coursera.algo2.week5.aman_code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amanpreet Singh on 10/13/13.
 */
public class TSP
{
	static float INFINITY = 100000;
	float[][] distanceMatrix;

	public double minimimumCost(List<String> inputs)
	{
		int cityCount = Integer.parseInt(inputs.get(0));
		System.out.println("Processing " + cityCount + " Cities");
		float[][] readStore = prepareStore((int) GospersHack.combination(cityCount, 1), cityCount + 1);
		float[][] writeStore = null;
		prepareDistanceMatrix(inputs, cityCount);

		//Base Case [Subproblem with size 1 has only one value other all infinity]
		readStore[0][1] = (float) 0.0;

		//Total Complexity = [O(n*n*2^n)]
		//Start with subproblem size 2 as subproblem size 1 is just base case  [O(n)]
		for (int subProblemSize = 2; subProblemSize <= cityCount; subProblemSize++)
		{
			//O(2^n) combinations in total for all subproblem sizes combined
			GospersHack.reset(subProblemSize, cityCount);
			//Each Subproblem iteration produces cityCount choose subProblemSize Combinations
			final int combinationSize = (int) GospersHack.combination(cityCount, subProblemSize);
			System.out.println("Heap Size (MB)" + (Runtime.getRuntime().totalMemory() / (1024 * 1024)));
			System.out.println(String.format("Problem Size %d with %d combinations", subProblemSize, combinationSize));
			writeStore = prepareStore(combinationSize, cityCount + 1);


			int i = 0;
			while (GospersHack.next())
			{
				Integer combination = GospersHack.getNext();

				//If combination doesn't contain 1st City then don't process
				if (combination % 2 == 0)
				{
					i++;
					continue;
				}

//				System.out.println("Processing combination " + Integer.toBinaryString(combination));

				//O(n) - Only loop over Cities which exist
				int endingVertex = 2;
				int c = combination >> 1;
				while (c > 0)
				{
					//To check weather an ending vertex exist in this combination.
					int lastBit = c % 2;

					//Loop only if ending vertex exists in this combination other wise remove vertex yields an invalid subgraph.
					if (lastBit == 1)
					{
						//Subgraph without ending Vertex
						int subgraph = removeVertex(combination, endingVertex);

						//Brute force search across all possible mid-vertices(k's)
						float min = INFINITY;

						//O(n) is work per sub problem
						int k = 1;
						int c1 = combination;
						while (c1 > 0)
						{
							//To check weather an ending vertex exist in this combination.
							lastBit = c1 % 2;
							if (lastBit == 1 && k != endingVertex)
							{
								final float kValue = readStore[GospersHack.getIndex(subgraph)][k];
								if (kValue != INFINITY)
								{
									//Because matrix is Zero Based
									float value = kValue + distanceMatrix[k - 1][endingVertex - 1];
									min = Math.min(min, value);
								}
							}
							c1 >>= 1;
							k++;
						}

						if (min != INFINITY)
						{
//							System.out.println(String.format("Adding %s@%d - %d = %f", Integer.toBinaryString(combination), i, endingVertex, min));
							writeStore[i][endingVertex] = min;
						}
					}
					c >>= 1;
					endingVertex++;
				}
				i++;
			}

			//Discard last values
			readStore = writeStore;
		}

		//Post Processing to complete the loop
		int fullGraph = GospersHack.gospersHack(cityCount, cityCount).get(0);
		float min = INFINITY;
		for (int endingVertex = 2; endingVertex <= cityCount; endingVertex++)
		{
			final float kValue = readStore[GospersHack.getIndex(fullGraph)][endingVertex];
			if (kValue != INFINITY)
			{
				float value = kValue + distanceMatrix[endingVertex - 1][1 - 1];
				min = Math.min(min, value);
			}
		}
		return Math.floor(min);
	}

	private float[][] prepareStore(int size, int cityCount)
	{
		float[][] store = new float[size][cityCount + 1];
		for (int i = 0; i < store.length; i++)
		{
			for (int j = 0; j < store[0].length; j++)
			{
				store[i][j] = INFINITY;
			}
		}
		return store;
	}

	private void prepareDistanceMatrix(List<String> inputs, int cityCount)
	{
		distanceMatrix = new float[cityCount][cityCount];
		Map<Integer, City> cityMap = getCities(inputs, cityCount);
		fill(cityMap, distanceMatrix);
	}

	private void fill(Map<Integer, City> cityMap, float[][] distanceMatrix)
	{
		for (int i = 0; i < cityMap.size(); i++)
		{
			for (int j = 0; j < cityMap.size(); j++)
			{
				City c1 = cityMap.get(i + 1);
				City c2 = cityMap.get(j + 1);
				distanceMatrix[i][j] = (float) getDistance(c1, c2);
			}
		}
	}

	int removeVertex(int graph, int vertex)
	{
		int mask = 1 << (vertex - 1);
		//Inverse Bits
		mask = -mask - 1;

		//Filter other bits except vertex
		return graph & mask;
	}


	private Map<Integer, City> getCities(List<String> inputs, int cityCount)
	{
		Map<Integer, City> cityMap = new HashMap<Integer, City>();
		for (int i = 1; i <= cityCount; i++)
		{
			String[] split = inputs.get(i).split(" ");
			double x = Double.parseDouble(split[0]);
			double y = Double.parseDouble(split[1]);

			cityMap.put(i, new City(x, y));
		}
		return cityMap;
	}

	private double getDistance(City c1, City c2)
	{
		//locations (x1,y1) and (x2,y2) have distance sqrt((x1−x2)^2+(y1−y2)^2) between them
		double dx = Math.abs(c2.x - c1.x);
		double dy = Math.abs(c2.y - c1.y);

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	class City
	{
		double x;
		double y;

		City(double x, double y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString()
		{
			return "City{" +
					"x=" + x +
					", y=" + y +
					'}';
		}
	}
}
