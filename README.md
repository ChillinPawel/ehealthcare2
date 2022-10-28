Second version of ehealthcare project

Introduced basic Spring Security for User handling.

In this version the structure will be more decoupled. User entity will be treated seperately from doctors and patients.

Data is now loaded from local MySql repository using JPA. Sample table as per below:

id username password roles enabled account_non_expired account_non_locked credentials_non_expired 1 admin pass1 ADMIN 1 1 1 1 2 user pass2 USER,DOCTOR 1 1 1 1

I will add seperate tables and entities for roles and authorities later, instead of storying it in one column.

The plan for later stages will be more or the same:

doctors of each specialization will create available visits
patients will book visits with specific doctors for specialization of choice
admins will be able to manipulate visits or any other users of non-admin role
