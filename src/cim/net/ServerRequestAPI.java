package cim.net;

import cim.database.DatabaseHandler;
import cim.models.*;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

/**
 * This class defines the API for the entire server request
 * @author Haakon
 *
 */
public class ServerRequestAPI {
	
	private final Server server;
	private final DatabaseHandler db;
	
	public ServerRequestAPI(Server server) throws CloakedIronManException {
		this.server = server;
		this.db = new DatabaseHandler();
		this.db.setServer(server);
	}
	
	
	
	/**
	 * Returns response object to the output stream.
	 * @param req
	 * @return
	 */
	public Response getResponse(Request req) {
		try {
			String method = req.getMethod().toUpperCase();
			Object[] args = req.getArgs();
            //	Big fat-ass if
			if(method.equals("AUTHENTICATE")) {
				return authenticate((String)args[0], (String)args[1]);
			} else if (method.equals("GET_ALL_CALENDARS")) {
				return get_all_calendars();
			} else if (method.equals("GET_ALL_CALENDARS_TO_ACCOUNT")) {
				return get_all_calendars_to_account((Account)args[0]);
			} else if (method.equals("SAVE_CALENDAR")) {
				return save_calendar((Calendar)args[0]);
			} else if (method.equals("GET_ACCOUNT")) {
				return get_account((int)args[0]);
			}
			else if (method.equals("GET_ALL_USERS"))
			{
				return get_all_users();
			}
            else if(method.equals("GET_REJECTMESSAGES_TO_ACCOUNT"))
            {
                return get_rejectMessages_to_account((Account)args[0]);
            }
			else if (method.equals("GET_MEETINGRESPONSESS_TO_ACCOUNT")){
				return get_all_meetingResponses_to_account((Account)args[0]);
			}
			
			
			else if(method.equals("SAVE_MEETING_RESPONSE")){
				return save_meeting_response((MeetingResponse)args[0]);
			}
			else if (method.equals("CANCEL_APPOINTMENT")){
				return cancel_appointment((Appointment)args[0]);
			}
			else if(method.equals("GET_ALL_ROOMS"))
            {
                return get_all_rooms();
            }
            else if(method.equals("GET_AVAILABLE_ROOMS"))
            {
                return get_available_rooms((Date)args[0], (Time)args[1], (Time)args[2]);
            }
			else if (method.equals("GET_ALL_GROUPS")) {
				return get_all_groups();
			}
			
			else if(method.equals("GET_ALERTS_TO_ACCOUNT")) {
				return get_alerts_to_account((Account)args[0]);
			}
			
			else if(method.equals("SAVE_ALERT")) {
				return save_alert((Alert)args[0]);
			}
			
			return new Response(new CloakedIronManException("No server API call named '" + method + "'"));
		} catch (Exception e) {
			return new Response(e);
		}
		
		
	}
	



	private Response cancel_appointment(Appointment appointment) throws CloakedIronManException {
		this.db.cancelAppointment(appointment);
		return new Response();
	}

	private Response get_all_meetingResponses_to_account(Account account) throws CloakedIronManException {
		return new Response(this.db.getMeetingResponsesToAccount(account));
	}

    private Response get_rejectMessages_to_account(Account account) throws CloakedIronManException {
        return new Response(this.db.getAllRejectMessagesToAccount(account));
    }

	/**
	 * Returns an account if the user is validated
	 * @param email
	 * @param pw
	 * @return
	 * @throws SQLException
	 */
	private Response authenticate(String email, String pw) throws CloakedIronManException {
		Account acc = db.getAccountByEmail(email);
		if(acc != null){
			if(acc.isValidPassword(pw)){
				return new Response(acc);
			}
		}
		return new Response((Object)null);
	}
	/**
	 * Returnerer alle kalendre registrert i systemet.
	 * @return
	 * @throws SQLException
	 */
	private Response get_all_users() throws CloakedIronManException
	{
		return new Response(this.db.getAllUsers());
	}
	private Response get_all_calendars() throws CloakedIronManException {
		//return new Response(new ArrayList());
		return new Response(this.db.getAllCalendars());
	}
	
	private Response get_all_calendars_to_account(Account acc) throws CloakedIronManException {
		return new Response(this.db.getAllCalendarsToAccount(acc));
	}
	
	private Response save_calendar(Calendar c) throws CloakedIronManException {
		Calendar c2 = this.db.saveCalendar(c);
		return new Response(c2);
	}
	
	private Response get_account(int id) throws CloakedIronManException {
		return new Response(this.db.getAccount(id));
	}
	
	private Response save_meeting_response(MeetingResponse mr) throws CloakedIronManException {
		this.db.saveMeetingResponse(mr);
		return new Response();
	}
	
	private Response get_all_groups() throws CloakedIronManException {
		return new Response(this.db.getAllGroups());
	}

    private Response get_available_rooms(Date date, Time start, Time end) throws CloakedIronManException
    {
        return new Response(this.db.getAvailableRooms(date, start, end));
    }

    private Response get_all_rooms() throws CloakedIronManException{
        return new Response(this.db.getAllRooms());
    }
    
    private Response get_alerts_to_account(Account account) throws CloakedIronManException {
		return new Response(this.db.getAlertsToAccount(account));
	}
    
    private Response save_alert(Alert a) throws CloakedIronManException{
    	return new Response(this.db.saveAlert(a));
    }
	
}
