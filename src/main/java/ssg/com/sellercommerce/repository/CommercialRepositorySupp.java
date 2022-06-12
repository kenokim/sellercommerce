package ssg.com.sellercommerce.repository;

import ssg.com.sellercommerce.domain.Commercial;

import java.util.List;

public interface CommercialRepositorySupp {
    List<Commercial> displayCommercials(int number);
}
