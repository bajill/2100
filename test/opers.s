# Code file created by Pascal2100 compiler 2015-09-04 13:36:36
        .extern write_char     
        .extern write_int      
        .extern write_string   
        .globl  _main          
        .globl  main           
_main:                                  
main:   call    prog$operatortest_1     # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$test_3:
        enter   $32,$3                  # Start of test
        .data                  
.L0004: .asciz   "not "
        .align  2              
        .text                  
        leal    .L0004,%eax             # Addr("not ")
        pushl   %eax                    # Push param #1.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #2.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0005: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0005,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #3.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        xorl    $0x1,%eax               #   not
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of test
        ret                             
proc$testunaryboolean_2:
        enter   $32,$2                  # Start of testunaryboolean
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    # Push param #1.
        call    proc$test_3             
        addl    $4,%esp                 # Pop parameters.
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    # Push param #1.
        call    proc$test_3             
        addl    $4,%esp                 # Pop parameters.
        leave                           # End of testunaryboolean
        ret                             
proc$test_7:
        enter   $32,$3                  # Start of test
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0008: .asciz   " and "
        .align  2              
        .text                  
        leal    .L0008,%eax             # Addr(" and ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0009: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0009,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        andl    %ecx,%eax               #   and
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0010: .asciz   " or "
        .align  2              
        .text                  
        leal    .L0010,%eax             # Addr(" or ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0011: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0011,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        orl     %ecx,%eax               #   or
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of test
        ret                             
proc$testbinaryboolean_6:
        enter   $32,$2                  # Start of testbinaryboolean
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    # Push param #1.
        call    proc$test_7             
        addl    $8,%esp                 # Pop parameters.
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    # Push param #1.
        call    proc$test_7             
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    # Push param #2.
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    # Push param #1.
        call    proc$test_7             
        addl    $8,%esp                 # Pop parameters.
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    # Push param #2.
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    # Push param #1.
        call    proc$test_7             
        addl    $8,%esp                 # Pop parameters.
        leave                           # End of testbinaryboolean
        ret                             
proc$test_13:
        enter   $32,$3                  # Start of test
        .data                  
.L0014: .asciz   "- "
        .align  2              
        .text                  
        leal    .L0014,%eax             # Addr("- ")
        pushl   %eax                    # Push param #1.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #2.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0015: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0015,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #3.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0016: .asciz   "+ "
        .align  2              
        .text                  
        leal    .L0016,%eax             # Addr("+ ")
        pushl   %eax                    # Push param #1.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #2.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0017: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0017,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #3.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #4.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #5.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of test
        ret                             
proc$testunarynumeric_12:
        enter   $32,$2                  # Start of testunarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_13            
        addl    $4,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_13            
        addl    $4,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_13            
        addl    $4,%esp                 # Pop parameters.
        leave                           # End of testunarynumeric
        ret                             
proc$test_19:
        enter   $32,$3                  # Start of test
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0020: .asciz   " + "
        .align  2              
        .text                  
        leal    .L0020,%eax             # Addr(" + ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0021: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0021,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0022: .asciz   " - "
        .align  2              
        .text                  
        leal    .L0022,%eax             # Addr(" - ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0023: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0023,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        subl    %ecx,%eax               #   -
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0024: .asciz   " * "
        .align  2              
        .text                  
        leal    .L0024,%eax             # Addr(" * ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0025: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0025,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        imull   %ecx,%eax               #   *
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
                                        # Start if-statement
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setne   %al                     # Test <>
        cmpl    $0,%eax                 
        je      .L0026                  
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0027: .asciz   " div "
        .align  2              
        .text                  
        leal    .L0027,%eax             # Addr(" div ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0028: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0028,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0029: .asciz   " mod "
        .align  2              
        .text                  
        leal    .L0029,%eax             # Addr(" mod ")
        pushl   %eax                    # Push param #2.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        pushl   %eax                    # Push param #3.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        .data                  
.L0030: .asciz   " = "
        .align  2              
        .text                  
        leal    .L0030,%eax             # Addr(" = ")
        pushl   %eax                    # Push param #4.
        call    write_string            
        addl    $4,%esp                 # Pop parameter.
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   x
        pushl   %eax                    
        movl    -12(%ebp),%edx          
        movl    12(%edx),%eax           #   y
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    # Push param #5.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #6.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
.L0026:                                 # End if-statement
        leave                           # End of test
        ret                             
proc$testbinarynumeric_18:
        enter   $32,$2                  # Start of testbinarynumeric
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $17,%eax                #   17
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $11,%eax                #   11
        negl    %eax                    #   - (prefix)
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #2.
        movl    $0,%eax                 #   0
        pushl   %eax                    # Push param #1.
        call    proc$test_19            
        addl    $8,%esp                 # Pop parameters.
        leave                           # End of testbinarynumeric
        ret                             
prog$operatortest_1:
        enter   $32,$1                  # Start of operatortest
        call    proc$testunaryboolean_2 
        call    proc$testunarynumeric_12 
        call    proc$testbinaryboolean_6 
        call    proc$testbinarynumeric_18 
        leave                           # End of operatortest
        ret                             
