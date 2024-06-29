; ModuleID = 'Other_statements.c'
source_filename = "Other_statements.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %5) #2
  store i32 10, ptr %2, align 4, !tbaa !3
  store i32 4, ptr %4, align 4, !tbaa !3
  store i32 6, ptr %5, align 4, !tbaa !3
  br label %6

6:                                                ; preds = %23, %0
  %7 = load i32, ptr %2, align 4, !tbaa !3
  %8 = add nsw i32 %7, -1
  store i32 %8, ptr %2, align 4, !tbaa !3
  store i32 0, ptr %3, align 4, !tbaa !3
  br label %9

9:                                                ; preds = %25, %6
  %10 = load i32, ptr %3, align 4, !tbaa !3
  %11 = load i32, ptr %2, align 4, !tbaa !3
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %28

13:                                               ; preds = %9
  br label %14

14:                                               ; preds = %17, %13
  %15 = load i32, ptr %4, align 4, !tbaa !3
  %16 = add nsw i32 %15, -1
  store i32 %16, ptr %4, align 4, !tbaa !3
  br label %17

17:                                               ; preds = %14
  %18 = load i32, ptr %4, align 4, !tbaa !3
  %19 = icmp slt i32 %18, 0
  br i1 %19, label %14, label %20, !llvm.loop !7

20:                                               ; preds = %17
  %21 = load i32, ptr %3, align 4, !tbaa !3
  %22 = icmp eq i32 %21, 3
  br i1 %22, label %23, label %24

23:                                               ; preds = %20
  br label %6

24:                                               ; preds = %20
  br label %25

25:                                               ; preds = %24
  %26 = load i32, ptr %3, align 4, !tbaa !3
  %27 = add nsw i32 %26, 1
  store i32 %27, ptr %3, align 4, !tbaa !3
  br label %9, !llvm.loop !9

28:                                               ; preds = %9
  %29 = load i32, ptr %5, align 4, !tbaa !3
  switch i32 %29, label %39 [
    i32 6, label %30
    i32 5, label %33
    i32 4, label %36
  ]

30:                                               ; preds = %28
  %31 = load i32, ptr %5, align 4, !tbaa !3
  %32 = add nsw i32 %31, -1
  store i32 %32, ptr %5, align 4, !tbaa !3
  br label %33

33:                                               ; preds = %28, %30
  %34 = load i32, ptr %5, align 4, !tbaa !3
  %35 = add nsw i32 %34, -1
  store i32 %35, ptr %5, align 4, !tbaa !3
  br label %36

36:                                               ; preds = %28, %33
  %37 = load i32, ptr %5, align 4, !tbaa !3
  %38 = add nsw i32 %37, -1
  store i32 %38, ptr %5, align 4, !tbaa !3
  br label %39

39:                                               ; preds = %28, %36
  store i32 15, ptr %5, align 4, !tbaa !3
  br label %40

40:                                               ; preds = %39
  call void @llvm.lifetime.end.p0(i64 4, ptr %5) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

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
!9 = distinct !{!9, !8}
