package com.marketplace.common.seed;

import java.util.List;

public final class DefaultServiceCategories {

    public static final List<ServiceCategoryDefinition> CATEGORIES = List.of(

        new ServiceCategoryDefinition(
            "PLUMBING",
            "Plumbing",
            "Installation, repair and maintenance of plumbing systems."
        ),

        new ServiceCategoryDefinition(
            "ELECTRICAL",
            "Electrical",
            "Electrical installation, maintenance and repair services."
        ),

        new ServiceCategoryDefinition(
            "CARPENTRY",
            "Carpentry",
            "Furniture, woodwork and structural carpentry services."
        ),

        new ServiceCategoryDefinition(
            "PAINTING",
            "Painting",
            "Residential and commercial painting services."
        ),

        new ServiceCategoryDefinition(
            "CLEANING",
            "Cleaning",
            "Home, office and industrial cleaning services."
        ),

        new ServiceCategoryDefinition(
            "LANDSCAPING",
            "Landscaping",
            "Garden maintenance and outdoor landscaping services."
        ),

        new ServiceCategoryDefinition(
            "HVAC",
            "HVAC",
            "Heating, ventilation and air conditioning services."
        ),

        new ServiceCategoryDefinition(
            "IT_SUPPORT",
            "IT Support",
            "Computer, networking and technology support services."
        ),

        new ServiceCategoryDefinition(
            "PHOTOGRAPHY",
            "Photography",
            "Professional photography services."
        ),

        new ServiceCategoryDefinition(
            "TUTORING",
            "Tutoring",
            "Private tutoring and educational support."
        ),

        new ServiceCategoryDefinition(
            "MOVING",
            "Moving",
            "Residential and commercial moving services."
        ),

        new ServiceCategoryDefinition(
            "APPLIANCE_REPAIR",
            "Appliance Repair",
            "Repair and maintenance of household appliances."
        )

    );

    private DefaultServiceCategories() {
    }
}
