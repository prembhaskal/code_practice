package intv.tsc;


//Tesco has around 3200 stores and more than 10% of the stores have around 800 colleagues each.

import java.util.*;

//        In a store, a colleague can work for multiple departments. Here are shifts of a colleague in various departments:
//
//
//
//        In Bakery department: From 8 to 10
//
//        In Checkout department: From 10 to 12
//
//        In Diary department: From 14 to 19
//
//        Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the day after merging contiguous shifts. This will be exposed to the colleague in different UI and help them plan their day accordingly.
//

//
//
//        His shift timings in this case are 8 to 12 and 14 to 19.
public class EmployeeShiftSolution {

    List<Employee> employees;

    public static void main(String[] args) {
        // 8  -               15
//                11  12
//        19-21, 23-2, 1-4 == exp 19--21,   23 -- 4
//        19-21(Day-0), 23-2(Day-0), 1-4(Day-1) == exp 19--21,   23 -- 4




        Interval intv1 = new Interval(19, 21, 0);
        EmployeeShift shift1 = new EmployeeShift("Bakery", intv1);
        Interval intv2 = new Interval(23, 2, 0);
        EmployeeShift shift2 = new EmployeeShift("Checkout", intv2);
        Interval intv3 = new Interval(1, 4, 1);
        EmployeeShift shift3 = new EmployeeShift("Diary", intv3);
        Interval intv4 = new Interval(13, 14);
        EmployeeShift shift4 = new EmployeeShift("Electronics", intv4);

        List<EmployeeShift> shifts = Arrays.asList(shift1, shift2, shift3);

        Employee employee = new Employee("prem", shifts);

        List<Interval> mergedIntervals = employee.getMyShifts();

        System.out.printf("employee intervals are: %s\n", mergedIntervals);
//        8,10 10,11 12,13 13,14
    }


}




class Employee {
    private final String id;
    private List<EmployeeShift> shifts;

    public Employee(String id, List<EmployeeShift> shifts) {
        this.id = id;
        this.shifts = shifts;
    }


    public List<Interval> getMyShifts() {
        if (shifts.isEmpty()) {
            return new ArrayList<>();
        }
        Collections.sort(shifts, new Comparator<EmployeeShift>() {
            @Override
            public int compare(EmployeeShift o1, EmployeeShift o2) {
                if (o1.interval.day < o2.interval.day) {
                    return -1;
                } else if (o1.interval.day > o2.interval.day) {
                    return 1;
                } else {
                    return Integer.compare(o1.interval.fromTime, o2.interval.fromTime);
                }
            };
        });

        List<Interval> mergedShifts = new ArrayList<>();
        //  23                   2
    //                     21                 4
        Interval prevInterval = shifts.get(0).interval; // 8 -- 15
        for (int i = 1; i < shifts.size(); i++) {
            EmployeeShift currShift = shifts.get(i);  // 11 -- 12
            if (currShift.interval.fromTime <= prevInterval.toTime ) {
                prevInterval.toTime = Math.max(prevInterval.toTime, currShift.interval.toTime);
            } else {
                mergedShifts.add(prevInterval); // 8 -- 12
                prevInterval = currShift.interval; // 14 -- 19
            }
        }

        mergedShifts.add(prevInterval); // 8 -- 12, 14 -- 19

        return mergedShifts;
    }


}
class Interval {
    int fromTime;
    int toTime;

    int day;

    public Interval(int fromTime, int toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Interval(int fromTime, int toTime, int day) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.day = day;
    }

    @Override
    public String toString() {
        return String.format("from: %d, to: %d", fromTime, toTime);
    }
}

class EmployeeShift {
    String department;

    Interval interval;

    public EmployeeShift(String department, Interval interval) {
        this.department = department;
        this.interval = interval;
    }
}
