; ModuleID = 'continue_statement.c'
source_filename = "continue_statement.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

@a = dso_local local_unnamed_addr global i32 0, align 4

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  store i32 2, ptr @a, align 4, !tbaa !3
  br label %2

2:                                                ; preds = %5, %0
  %3 = load i32, ptr @a, align 4, !tbaa !3
  %4 = icmp ne i32 %3, 0
  br i1 %4, label %5, label %8

5:                                                ; preds = %2
  %6 = load i32, ptr @a, align 4, !tbaa !3
  %7 = add nsw i32 %6, -1
  store i32 %7, ptr @a, align 4, !tbaa !3
  br label %2, !llvm.loop !7

8:                                                ; preds = %2
  %9 = load i32, ptr @a, align 4, !tbaa !3
  ret i32 %9
}

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
!7 = distinct !{!7, !8}
!8 = !{!"llvm.loop.mustprogress"}
