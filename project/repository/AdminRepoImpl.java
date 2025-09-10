package com.xworkz.project.repository;

import com.xworkz.project.entity.AdminEntity;
import com.xworkz.project.entity.SignUpEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
@AllArgsConstructor
public class AdminRepoImpl implements AdminRepo {

    private EntityManagerFactory emf;

    @Override
    public AdminEntity findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AdminEntity> query = em.createNamedQuery("AdminEntity.findByEmail", AdminEntity.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No admin found with email: " + email);
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public boolean updateOtpByEmail(String email, int otp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<AdminEntity> query = em.createNamedQuery("AdminEntity.findByEmail", AdminEntity.class);
            query.setParameter("email", email);
            AdminEntity admin = query.getSingleResult();

            if (admin != null) {
                admin.setOtp(otp);
                admin.setUpdatedAt(LocalDateTime.now());
                em.merge(admin);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean verifyOtp(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AdminEntity> query = em.createNamedQuery("AdminEntity.findByEmail", AdminEntity.class);
            query.setParameter("email", email);
//            query.setParameter("otp",otp);
            AdminEntity admin = query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No admin found for email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public AdminEntity getByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AdminEntity> query = em.createNamedQuery("AdminEntity.findByEmail", AdminEntity.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<SignUpEntity> viewUser() {
        EntityManager em=emf.createEntityManager();
        TypedQuery<SignUpEntity> query=em.createNamedQuery("viewUser", SignUpEntity.class);
        System.out.println(query);
        return query.getResultList();
    }
}
