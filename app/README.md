# cinema_management

1. Project's title: "Universal Application"

2. How to install and Run the project
- Download zip file from canvas submission site. Connect app to fireStore database, make sure Internet permission rule have already written in AndroidManifest.xml

3. Feature include:

- Searching movies by it name, read movie's details when click on the movie item itself. For example, read information relate to the movie like rate, duration movie name, watch movie trailer, leaves review for some particular movie

- Searching cinema by cinema name, or using filter to find all cinema available in a particular city. Read details about any particular cinema. Moreover, find cinemas on maps and find direction using google map.

- Read news about any movies, celebrities on our app.

- Read discount, combo available on our app.

 -Lots of User functions (please check user case: User)

 -Lots of Admin functions (please user case: Admin)

- Login logout if users already have an account, if users do not have an account they can register an new account.

- Validation user input when register account

- Interaction between admin and user through feedback function
4. User case:

- Guest:
    + Available to search movies, cinemas.
    + Read movies, cinemas, news, discount details
    + Register for new account to become an regular users of our app.
- Users:
    + Login
    + sign in
    + logout
    + forgot password
    + add review for any movies, see their reviews, see other reviews + can like or dislike that review
    + write feedback for the app + check all their feedbacks.
    + make transactions to buy ticket for any movies at any cinemas available + check their transactions' history.
    + Use point after buying movies tickets to exchange vouchers.
    + Update the profile.
    + Receive reply feedback from admin through notification bell
    + Upgrade the account type to experience even more discount
    +Can use all functions from guest

- Admin:
    + managing database for cinema(add, delete, update, read)
    + managing database for combo(add, delete, update, read)
    + managing database for discount(add, delete, update, read)
    + managing database for voucher(add, delete, update, read)
    + reply feedback from users feedback

5. Feature not finished on time:
- Our group project have no incomplete feature. we have manage to finish all the feature that we have planed before on time.
6. Bug in project:
- Completely no bugs in the project. However, this project require to run on strong computer, for example the school imac (this is recommendation since we working on our project the school Imac in room 1.4.16).
- If the computer is not strong enough or the internet connect is weak, there are problem with getting data from database, and call authentication from firebase to check for account sign in or register new account.feedback

7. Note
- admin account is:
    + Username: s3823236@admin.com
    + Password: admin104
    (this information can be found in User model class).
- User account can be register by pressing account button from bottom nav and choose register or use these following account that has been registered.
    + Username: bmhuyquoc104@gmail.com
    + Password: 637331717@Huy

8. App's drawback
- Our app could be a bit laggy when accessing for database. Because we enormous date being store in our database.
- We can not find any available api for movie tickets or any website that allows scrapping. Therefore, that app is not fully optimize when working with movie tickets.

9 Version & Recommend emulator
- Version 1.0.0
- Best experienced on 5.5, 1440 * 2560, 560dpi (Pixel XL)

10. Contribution
    + Huy:
        *App over all
        -Design the theme for the app
        -Design the logo for the app
        -Design the splash screen
        -Design and implement bottom navigation bar
        -Design the process of using the app
        -Provide the idea of all functions in the app
        -Provide the idea of all databases in the app

        *User
        -Scarping the data for streaming and upcoming movie
        -Flawlessly Finished implementation for home page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for cinema page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for account page (include sign up and register) with lots of fragments, modals, adapters, functions, validations and design (check the package in source code to see more details)
        - Flawlessly Finished implementation for search page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user profile page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user homepage with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user combo page with lots of fragments, modals, adapters, functions, connect to the database and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user voucher page with lots of fragments, modals, adapters, functions, connect to the database and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user review page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user feedback page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user notification page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user guide page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for user transaction page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished the logic, design, connect to database, of buying ticket process (check the package in source code to see more details)

        *Admin
        -Flawlessly Finished implementation for admin discount page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for admin combo page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for admin voucher page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for admin cinema page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)
        -Flawlessly Finished implementation for admin feedback page with lots of fragments, modals, adapters, functions and design (check the package in source code to see more details)

        *Top difficult functions
        -Buying movie tickets logic + validations + allows users to follow the process + seat booking
        -Exchanging the voucher and update the remain point on UI + database in real time (not using real time database)
        -Logic to display somepart if and only if the user is logged in (review,buy ticket)
        -Logic to remove date, time that pass current date time in showing time for ticket option
        -Like and dislike (each user can only like or dislike once)

        *Fix bug
        -Fix all bugs by other teammates
        -Redesign the whole function if the logic is not corrected, or they do not understand how the app works, or the function is completely unfixed
        -Fix the UI, and redo the UI sometimes
        -Merge and resolve all conflicts in Github

    + Hung:
        -Flawlessly Finished implementation for footer with 4 activities and well written out content (check the package in source code to see more details)
        -Flawlessly Finished implementation for discount viewing page with filter by month (check the package in source code to see more details)
        -Flawlessly Finished implementation for discount details viewing page with on click events (check the package in source code to see more details)
        -Flawlessly Finished implementation for news viewing page (check the package in source code to see more details)
        -Flawlessly Finished implementation for news details viewing page with on click events (check the package in source code to see more details)
        -Flawlessly Finished implementation for content and UI for policies viewing with lots of dialogues (check the package in source code to see more details)
        -Flawlessly Finished implementation for content and UI for displaying additional information of the company with lots of dialogues (check the package in source code to see more details)
        -Flawlessly Finished implementation for content and UI for displaying Free Flights, Points and Tickets with lots of dialogues (check the package in source code to see more details)
        -Flawlessly Helped implementation for CRUD pages for the discounts, mostly on UI with multiple activities (check the package in source code to see more details)
        -Flawlessly Wrote and designed content for discounts and news (made their database).
        -Flawlessly Finished implementation for the UI for the ticket price tab (check the package in source code to see more details)
        -Flawlessly Drew the illustrations for ticket price and combos on Figma.

    + Khang:
        - working mainly on CRUD database like cinema, voucher, users, combo, feedback, reply feedback (admin reply feedback from user), reviews, transaction.
        - users login, sign up, logout, forgot password, register.

                                    Huy         Khang       Hung

Hung self-evaluate contribution     100%        90%         80%

Khang self-evaluate contribution    100%        70%         70%

Huy self-evaluation contribution    100%        75%         50%
(Leader)



