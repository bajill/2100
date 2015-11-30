# Code file created by Pascal2100 compiler 2015-11-30 14:38:06
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl  _main                   
        .globl  main                    
_main:                                  
main:   call    prog$whi_1              # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
prog$whi_1:                                
        enter   $40, $1                 # Start of name??
        movl    $0,%eax                 # numberValue: 0
        movl    -4(%ebp),%edx           # move variable to %edx
        movl    %eax,-36(%edx)          # i :=
        movl    $1,%eax                 # numberValue: 1
        movl    -4(%ebp),%edx           # move variable to %edx
        movl    %eax,-40(%edx)          # j :=
.L0002:                                 # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          # i
        movl    $5,%eax                 # numberValue: 5
        pushl   %eax                    
        movl    $4,%eax                 # numberValue: 4
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setl    %al                     # Test <
        cmpl    $0, %eax                
        je      .L0003                  
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          # i
        pushl   %eax                    
        movl    $1,%eax                 # numberValue: 1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               # +
        movl    -4(%ebp),%edx           # move variable to %edx
        movl    %eax,-36(%edx)          # i :=
        jmp     .L0002                  
.L0003:                                 # end while-statement
        leave                           
        ret                             
