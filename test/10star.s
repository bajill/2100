# Code file created by Pascal2100 compiler 2015-09-04 13:36:35
        .extern write_char     
        .extern write_int      
        .extern write_string   
        .globl  _main          
        .globl  main           
_main:                                  
main:   call    prog$tenstars_1         # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$tenstars_1:
        enter   $36,$1                  # Start of tenstars
        movl    $0,%eax                 #   0
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
.L0002:                                 # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $10,%eax                #   10
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setl    %al                     # Test <
        cmpl    $0,%eax                 
        je      .L0003                  
        movl    $42,%eax                #   char 42
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        jmp     .L0002                  
.L0003:                                 # End while-statement
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of tenstars
        ret                             
