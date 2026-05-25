package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IBusinessLogic {
    ArrayList<SchoolOfThought> getAllSchools() throws DataAccessException;
}
