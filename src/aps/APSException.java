package aps;

import java.io.Serializable;

/** General exception indicating an APS error.  For fatal errors, the
 *  result of .getMessage() is the error message to be printed.
 *  Based off Exception classes in UC Berkeley CS61B course with
 *  Prof. P. N. Hilfinger.
 *  @author A. J. Wang
 */
class APSException extends RuntimeException implements Serializable {


    /** A GitletException with no message. */
    APSException() {
        super();
    }

    /** A GitletException MSG as its message. */
    APSException(String msg) {
        super(msg);
    }

}
