class Solution {

    class Point {

        int[] point;
        double distance;

        public Point(int[] point, double distance){
            this.point = point;
            this.distance = distance;
        }

        public int[] getPoint(){
            return point;
        }

        public double getDistance() {
            return distance;
        }

    }

    public int[][] kClosest(int[][] points, int k) {

        /**
            P:
                - given n points, which is the k-th closest point to the origin?
            E:
                - the examples are pretty clear here
            D:
                - min-heap/priority-queue
            A:
                - init priorit queue based on comparison of distance
                - poll k times, and add to return list
            C:
        */

        PriorityQueue<Point> pointQueue = new PriorityQueue(Comparator.comparingDouble(Point::getDistance));
        int[] origin = new int[]{0,0};
        for (int i = 0; i < points.length; i++) {
            pointQueue.offer(new Point(points[i], distance(points[i], origin)));
        }

        int[][] kClosestPoints = new int[k][points[0].length];
        for (int i = 0; i < k; i++){
            kClosestPoints[i] = pointQueue.poll().getPoint();
        }

        return kClosestPoints;
    }

    public double distance(int[] point1, int[] point2) {
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2, 2));
    }
}