# Code file created by Pascal2100 compiler 2015-09-04 13:36:35
        .extern write_char     
        .extern write_int      
        .extern write_string   
        .globl  _main          
        .globl  main           
_main:                                  
main:   call    prog$mini_1             # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$mini_1:
        enter   $32,$1                  # Start of mini
        movl    $120,%eax               #   char 120
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of mini
        ret                             
