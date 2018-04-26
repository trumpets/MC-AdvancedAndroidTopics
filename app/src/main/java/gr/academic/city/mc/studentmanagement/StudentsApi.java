package gr.academic.city.mc.studentmanagement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by trumpets on 5/11/16.
 */
public interface StudentsApi {

    static final String BASE_URL = "https://city-201617.appspot.com/_ah/api/students/v1/";
    static final String STUDENTS_URL = "student";

    @GET(STUDENTS_URL)
    Call<ListResponse> getAllStudents();

    @POST(STUDENTS_URL)
    Call<Void> createStudent(@Body Student student);
}
