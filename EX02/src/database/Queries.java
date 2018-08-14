package database;

public class Queries {

	//Create statement
	public static String CREATE_DATABASE = " ";
	public static String CREATE_QUEUE_RESERVED_TABLE = "(queue_id,pastient _id, queue_time, doctor_id)";
	public static String CREATE_DOCTOR_TABLE = "(doctor_id,d_f_name, d_l_name,doctor_type)";
	public static String CREATE_PATIENTS_TABLE = "(pastient_id, p_f_name, p_l_name, gender)";
	public static String CREATE_QUEUE_TABLE = "(queue_id,time) ";
	
	//Select statement
	public static String SELECT_ALL = " ";
	public static String SELECT_WAITING_PATIENTS_BY_TIME = "";
	public static String SELECT_ALL_VIEW_DOCTOR_ASSOSIETES = " ";
	
	//Update statement
	
	//Delete statement
	public static String DELETE_QUEUE = " ";
	
	//Trigger statement
	public static String TRIGGER_MANAGE_QUEUE = " ";
	
	//View statement
	public static String VIEW_DOCTOR_ASSOSIETES = " ";
}
