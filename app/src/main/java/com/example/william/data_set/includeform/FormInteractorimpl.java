package com.example.william.data_set.includeform;

/**
 * Created by WilliamStibent on 02/09/2016.
 */
public class FormInteractorimpl implements FormInteractor {
    private FormRepository formRepository;

    public FormInteractorimpl() {
        this.formRepository = new FormRepositoryImpl();
    }

    @Override
    public void doRegister(String userName, String name, String lastName,
                           int age, String gender,int weight, int stature, int waist,
                           String profession, String mail, String smoke,
                           String drink, String transport) {
        formRepository.Register(userName, name, lastName, age, gender, weight,
                stature, waist, profession,mail,smoke,drink,transport);
    }
}
