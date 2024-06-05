# School Management System API Documentation

This project is a school management system developed using Spring Boot, with security features implemented using Spring Security JWT (JSON Web Tokens).

## Testing Accounts

### Role Admin
- Username: admin
- Password: 123456

### Role Mod
- Username: mod
- Password: 123456

### Role User
- Username: user
- Password: 123456

## API Endpoints

1. **Auth**

    - **POST** `/api/auth/signin`
        - Description: Login to obtain bearer token
        - Bearer token is automatically set when logging in.

    - **POST** `/api/auth/signup`
        - Description: Register an account to access the API
        - Role: admin / mod / user

2. **Manajemen Guru (Teacher Management)**

    - **GET** `/api/guru`
        - Description: Get a list of teachers
        - Role: User, Mod, Admin

    - **GET** `/api/guru/{id}`
        - Description: Get information about a teacher based on ID
        - Role: User, Mod, Admin

    - **POST** `/api/guru`
        - Description: Add a new teacher
        - Role: Mod, Admin

    - **PUT** `/api/guru/{id}`
        - Description: Update information about a teacher based on ID
        - Role: Mod, Admin

    - **DELETE** `/api/guru/{id}`
        - Description: Delete a teacher based on ID
        - Role: Admin

3. **Manajemen Siswa (Student Management)**

    - **GET** `/api/siswa`
        - Description: Get a list of students
        - Role: User, Mod, Admin

    - **GET** `/api/siswa/{id}`
        - Description: Get information about a student based on ID
        - Role: User, Mod, Admin

    - **POST** `/api/siswa`
        - Description: Add a new student
        - Role: Mod, Admin

    - **PUT** `/api/siswa/{id}`
        - Description: Update information about a student based on ID
        - Role: Mod, Admin

    - **DELETE** `/api/siswa/{id}`
        - Description: Delete a student based on ID
        - Role: Admin

4. **Jadwal Pelajaran (Class Schedule Management)**

    - **GET** `/api/jadwal-pelajaran`
        - Description: Get class schedules
        - Role: User, Mod, Admin

    - **GET** `/api/jadwal-pelajaran/{id}`
        - Description: Get information about a class schedule based on ID
        - Role: User, Mod, Admin

    - **POST** `/api/jadwal-pelajaran`
        - Description: Add a new class schedule
        - Role: Mod, Admin

    - **PUT** `/api/jadwal-pelajaran/{id}`
        - Description: Update information about a class schedule based on ID
        - Role: Mod, Admin

    - **DELETE** `/api/jadwal-pelajaran/{id}`
        - Description: Delete a class schedule based on ID
        - Role: Admin

5. **Pengelolaan Nilai (Grading Management)**

    - **GET** `/api/nilai`
        - Description: Get a list of grades
        - Role: User, Mod, Admin

    - **GET** `/api/nilai/{id}`
        - Description: Get information about a grade based on ID
        - Role: User, Mod, Admin

    - **POST** `/api/nilai`
        - Description: Add a new grade
        - Role: Mod, Admin

    - **PUT** `/api/nilai/{id}`
        - Description: Update information about a grade based on ID
        - Role: Mod, Admin

    - **DELETE** `/api/nilai/{id}`
        - Description: Delete a grade based on ID
        - Role: Admin

