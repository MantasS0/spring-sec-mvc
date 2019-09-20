package lt.mantass0.springsecmvc.utility;

public class PaginationRange {
    private Integer rangeFrom;
    private Integer rangeTo;

    public PaginationRange(int pageNumber, int pageCount) {
        if (pageNumber == 1) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (pageNumber == 2) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (pageNumber == 3) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (pageNumber == pageCount - 2) {
            this.rangeFrom = pageCount - 4;
            this.rangeTo = pageCount;
        } else if (pageNumber == pageCount - 1) {
            this.rangeFrom = pageCount - 4;
            this.rangeTo = pageCount;
        } else if (pageNumber == pageCount) {
            this.rangeFrom = pageCount - 4;
            this.rangeTo = pageCount;
        } else {
            this.rangeFrom = pageNumber - 2;
            this.rangeTo = pageNumber + 2;
        }
    }

    public Integer getRangeFrom() {
        return rangeFrom;
    }

    public Integer getRangeTo() {
        return rangeTo;
    }

}
