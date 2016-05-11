package gr.academic.city.mc.studentmanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by trumpets on 5/11/16.
 */
public interface StudentsApi {

    static final String BASE_URL = "http://students-sdmdcity.rhcloud.com/rest/";
    static final String STUDENTS_URL = "students";

    @GET(STUDENTS_URL)
    Call<List<Student>> getAllStudents();

    @POST(STUDENTS_URL)
    Call<Void> createStudent(@Body Student student);
}
