/* 
package BookNexus;


import java.util.concurrent.TimeUnit;

public class FineCalculation {
    
   private static final double FINE_PER_DAY = 150;

   public double calculateFine(Transaction transaction) {
       if (transaction.getReturnDate() != null) {
           long diffInMillies = transaction.getReturnDate().getTime() - transaction.getDueDate().getTime();
           long daysLate = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
           return daysLate > 0 ? daysLate * FINE_PER_DAY : 0; 
       }
       return 0; 
   }
}
*/
package BookNexus;

import java.util.concurrent.TimeUnit;

public class FineCalculation {
    
    private static final double finePenalty = 150.0;
    
    public double calculateFine(Transaction transaction) {
        if (transaction.getReturnDate() != null) {
            long numberOfDaysDue = transaction.getReturnDate().getTime().getYear() - transaction.getDueDate().getTime().getYear();
            
            if (numberOfDaysDue > 0) {
                return numberOfDaysDue * finePenalty;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}

