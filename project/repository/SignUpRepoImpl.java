package com.xworkz.project.repository;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.SignUpEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDateTime;

@Repository

public class SignUpRepoImpl implements SignUpRepo {
    @PersistenceUnit
    private EntityManagerFactory emf;
    private static final String IMAGE_DIR = "C:\\Users\\DELL\\Desktop\\Java Ful stack Project\\project\\src\\main\\webapp\\uploads";

    @Override
    public boolean save(SignUpEntity signUpEntity) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(signUpEntity);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }


    @Override
    public SignUpEntity findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            String cleanEmail = email.trim().toLowerCase();
            System.out.println("Querying for email: [" + cleanEmail + "]");

            TypedQuery<SignUpEntity> query = em.createNamedQuery("signin", SignUpEntity.class);
            query.setParameter("email", cleanEmail);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with email: " + email);
        } catch (Exception e) {
            System.err.println("Error occurred while fetching user by email:");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }


    @Override
    public SignUpEntity findBycontactNumber(long contactNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SignUpEntity> query = em.createNamedQuery("checkContact", SignUpEntity.class);
            query.setParameter("contactNumber", contactNumber);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No contact Number found: " + contactNumber);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public boolean update(SignUpDto dto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Query query = em.createNamedQuery("update");
            query.setParameter("userName", dto.getUserName());
            query.setParameter("contactNumber", dto.getContactNumber());
            query.setParameter("alternateContactNumber", dto.getAlternateContactNumber());
            query.setParameter("adressLine1", dto.getAdressLine1());
            query.setParameter("adressLine2", dto.getAdressLine2());
            query.setParameter("city", dto.getCity());
            query.setParameter("state", dto.getState());
            query.setParameter("pin", dto.getPin());
            query.setParameter("email", dto.getEmail());
            query.setParameter("updatedAt", dto.getUpdatedAt());
            query.setParameter("updatedBy", dto.getUpdatedBy());


            int rowsUpdated = query.executeUpdate();
            transaction.commit();

            System.out.println("Rows updated: " + rowsUpdated);
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean deleteByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createNamedQuery("deleteByEmail");
        query.setParameter("email", email);
        int rows = query.executeUpdate();
        System.out.println(rows);
        transaction.commit();
        em.close();
        return true;
    }

    @Override
    public boolean softDeleteByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createNamedQuery("softDeleteByEmail");
            query.setParameter("email", email);
            int updated = query.executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            tx.rollback();
            return false;
        } finally {
            em.close();
        }


    }

    @Override
    public boolean updateOtpByEmail(String email, String otp) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Query query = em.createNamedQuery("updateOtp");
            query.setParameter("email", email);
            query.setParameter("otp", Integer.parseInt(otp));
            query.setParameter("time", LocalDateTime.now());
            int updated = query.executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            System.out.println("Updating password for email: " + email);
            Query query = em.createNamedQuery("updatePasswordByEmail");
            query.setParameter("password", newPassword);
            query.setParameter("email", email.trim().toLowerCase());

            int updated = query.executeUpdate();
            System.out.println("Rows updated: " + updated);

            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}

